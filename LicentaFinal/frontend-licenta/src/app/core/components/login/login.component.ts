import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthData, User} from "../../../shared/types/authentication.types";
import {AuthenticationService} from "../../services/authentication.service";
import {UserService} from "../../services/user.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  login: FormGroup;
  signUp: FormGroup;
  showRegisterForm: boolean = false;
  signUpError: string | null = null;
  loginError: string | null = null;

  constructor(private fb: FormBuilder, private userService: UserService, private toastr: ToastrService, private authService: AuthenticationService) {
    this.login = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });

    this.signUp = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, {validator: this.matchPasswords});
  }

  matchPasswords(formGroup: FormGroup) {
    const passwordControl = formGroup.get('password');
    const confirmPasswordControl = formGroup.get('confirmPassword');

    if (confirmPasswordControl?.errors && !confirmPasswordControl.errors['mustMatch']) {
      return;
    }

    if (passwordControl?.value !== confirmPasswordControl?.value) {
      confirmPasswordControl?.setErrors({mustMatch: true});
    } else {
      confirmPasswordControl?.setErrors(null);
    }
  }

  isFieldInvalid(form: FormGroup, field: string): boolean {
    const control = form.get(field);
    return control?.invalid && (control.dirty || control.touched) || false;
  }

  toggleForms(showRegister: boolean): void {
    this.showRegisterForm = showRegister;
  }

  clickSignUp() {
    if (this.signUp.invalid) {
      Object.values(this.signUp.controls).forEach(control => control.markAsTouched());
      return;
    }

    const userDto: User = {
      id: "",
      firstName: this.signUp.get('firstName')?.value,
      lastName: this.signUp.get('lastName')?.value,
      email: this.signUp.get('email')?.value,
      password: this.signUp.get('password')?.value
    };
    this.userService.createUser(userDto).subscribe({
      next: (response) => {
        this.toastr.success('Account created successfully!', 'Success');
        this.signUpError = null;
        this.showRegisterForm = false;
      },
      error: (error) => {
        this.signUpError = 'Sign up failed. Please try again.';
        this.toastr.error('Sign up failed. Please try again.', 'Error');
        console.error('Sign up failed:', error);
      }
    });
  }

  clickLogin(): void {
    if (this.login.invalid) {
      Object.values(this.login.controls).forEach(control => control.markAsTouched());
      return;
    }
    const authData = {
      email: this.login.get('email')?.value,
      password: this.login.get('password')?.value
    };

    this.authService.login(authData).subscribe({
      next: (response) => {
        this.loginError = null;
      },
      error: (error) => {
        this.loginError = 'Login failed. Please check your credentials.';
        this.toastr.error('Login failed. Please check your credentials.', 'Error');
      }
    });
  }

}
