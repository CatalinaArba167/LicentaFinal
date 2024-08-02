import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup,Validators } from "@angular/forms";
import {UserService} from "../../core/services/user.service";
import {AuthenticationService} from "../../core/services/authentication.service";
import {MatDialog} from "@angular/material/dialog";

import {CustomDialogComponent} from "../../shared/components/custom-dialog/custom-dialog.component";
import {ToastrService} from "ngx-toastr";
import {User} from "../../shared/types/authentication.types";
import {ProfilePictureService} from "../services/profile-picture.service";
import {tap} from "rxjs";

interface EditingStates {
  [key: string]: boolean;
}

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  @ViewChild('fileInput', { static: false }) fileInput!: ElementRef;
  userInfoForm!: FormGroup;
  userProfile: any;
  editing: EditingStates = {};
  profilePictureUrl: string | ArrayBuffer | null = 'assets/default_profile_picture.png'; // Default image
  originalProfilePictureUrl: string | ArrayBuffer | null = 'assets/default_profile_picture.png'; // To store original image
  isEditing: boolean = false;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private authService: AuthenticationService,
    private profilePictureService: ProfilePictureService,
    private dialog: MatDialog,
    private toastr: ToastrService,
  ) {
  }

  ngOnInit(): void {
    const userId = this.authService.getUserId();
    this.userService.getUser(userId!).subscribe({
      next: (user) => {
        this.userProfile = user;
        this.initializeForm();
        this.loadProfilePicture(userId!);

      },
      error: (err) => {
        console.error('Failed to fetch user data', err);
      }
    });
  }

  initializeForm(): void {
    this.userInfoForm = this.fb.group({
      firstName: [this.userProfile.firstName, [Validators.required]],
      lastName: [this.userProfile.lastName, [Validators.required]],
      email: [this.userProfile.email, [Validators.required, Validators.email]],
      password: [this.userProfile.password, [Validators.required, Validators.minLength(8)]]
    });
  }


  loadProfilePicture(userId: string): void {
    this.profilePictureService.getProfilePicture(userId).subscribe({
      next: (response) => {
        const reader = new FileReader();
        reader.addEventListener('load', () => {
          this.profilePictureUrl = reader.result;
          this.originalProfilePictureUrl = reader.result;
        }, false);

        if (response) {
          reader.readAsDataURL(response);
        }
      },
      error: (error) => {
        console.error('Error loading profile picture:', error);
        this.profilePictureUrl = 'assets/default_profile_picture.png';
        this.originalProfilePictureUrl = 'assets/default_profile_picture.png';
      }
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length) {
      const file = input.files[0];
      const reader = new FileReader();
      reader.onload = (e) => {
        if (e.target?.result) {
          this.profilePictureUrl = e.target.result;
          this.isEditing = true;
        }
      };
      reader.readAsDataURL(file);
    }
  }

  saveProfilePicture(): void {
    const userId = this.authService.getUserId();
    const input = document.querySelector('input[type="file"]') as HTMLInputElement;
    if (input.files && input.files.length) {
      const file = input.files[0];
      this.profilePictureService.uploadProfilePicture(userId!.toString(), file).subscribe({
        next: (response) => {
          console.log('Response from server:', response);
          this.isEditing = false;
          this.toastr.success('Profile picture updated successfully!', 'Success');
        },
        error: (err) => {
          console.error('Error response from server:', err);
          this.toastr.error('Failed to update profile picture. Please try again.', 'Error');
        }
      });
    }
  }

  cancelProfilePictureEdit(): void {
    this.profilePictureUrl = this.originalProfilePictureUrl;
    this.isEditing = false;
  }

  enableEdit(field: string) {
    this.editing[field] = true;
  }

  openSaveDialog(field: string) {
    const dialogRef = this.dialog.open(CustomDialogComponent, {
      width: '350px',
      data: {message: 'Are you sure you want to modify your profile?'}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.saveEdit(field);
      } else {
        this.cancelEdit(field);
      }
    });
  }

  saveEdit(field: string): void {
    if (this.userInfoForm.valid) {
      const userData: User = {
        id: this.authService.getUserId()!.toString(),
        firstName: this.userInfoForm.value.firstName,
        lastName: this.userInfoForm.value.lastName,
        email: this.userInfoForm.value.email,
        password: this.userInfoForm.value.password,
      };

      this.userService.updateUser(userData).subscribe({
        next: (res) => {
          this.editing[field] = false;
          this.toastr.success('Profile updated successfully!', 'Success');
        },
        error: (err) => {
          this.toastr.error('Failed to update profile. Please check your input and try again.', 'Error');
        }
      });
    } else {
      this.userInfoForm.markAllAsTouched();
      this.toastr.error('Please ensure all fields are filled out correctly.', 'Form Incomplete');
    }
  }


  cancelEdit(field: string) {
    this.editing[field] = false;
    const originalValue = this.userProfile[field];
    this.userInfoForm.controls[field].reset(originalValue);
  }

}
