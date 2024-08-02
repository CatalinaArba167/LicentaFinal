import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SellingPost} from "../../types/selling-post.types";
import {MatDialog} from "@angular/material/dialog";
import {CustomDialogComponent} from "../custom-dialog/custom-dialog.component";
import {NavigationExtras, Router} from "@angular/router";

@Component({
  selector: 'app-my-car-card',
  templateUrl: './my-car-card.component.html',
  styleUrls: ['./my-car-card.component.scss']
})
export class MyCarCardComponent implements OnInit{
  @Input() sellingPost!: SellingPost;
  @Output() deleteConfirmed = new EventEmitter<SellingPost>();
  firstImageSrc: string | undefined;
  processedPictures:string[]=[];
  defaultImageSrc: string = 'assets/default_selling_picture.png';
  constructor(public dialog: MatDialog,private router: Router) {}
  ngOnInit(): void {
    this.processPictures();
  }

  private processPictures(): void {
    const pictures = this.sellingPost?.sellingPictures || [];

    if (pictures.length > 0) {
      console.log(`Number of Pictures: ${pictures.length}`);

      // Process the first picture separately if needed
      this.processPicture(pictures[0], true);

      // Process remaining pictures
      pictures.forEach((picture, index) => {
        if (index !== 0) { // Skip the first picture
          this.processPicture(picture);
        }
      });
    } else {
      this.firstImageSrc = this.defaultImageSrc;
    }
  }

  private processPicture(picture: string | File, isFirstPicture: boolean = false): void {
    if (typeof picture === 'string') {
      // If it's a base64 string
      this.base64ToBlob(picture).then(blob => this.convertAndStorePicture(blob, isFirstPicture));
    } else {
      // If it's already a File object
      this.convertAndStorePicture(picture, isFirstPicture);
    }
  }

  private convertAndStorePicture(blob: Blob, isFirstPicture: boolean): void {
    this.fileToBase64(blob).then(base64 => {
      if (isFirstPicture) {
        this.firstImageSrc = base64;
      }
      this.processedPictures.push(base64);
    });
  }

  openConfirmDialog(): void {
    const dialogRef = this.dialog.open(CustomDialogComponent, {
      width: '300px',
      panelClass: 'confirm-dialog',
      data: { message: 'Are you sure you want to delete this car?' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteConfirmed.emit(this.sellingPost);
      }
    });
  }

  private fileToBase64(file: Blob): Promise<string> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result as string);
      reader.onerror = error => reject(error);
    });
  }

  private base64ToBlob(base64: string): Promise<Blob> {
    return fetch(`data:image/jpeg;base64,${base64}`).then(res => res.blob());
  }

  navigateToEditPage(): void {
    const navigationExtras: NavigationExtras = {
      state: { sellingPost: this.sellingPost, processedPictures:this.processedPictures
      }
    };
    this.router.navigate(['/edit', this.sellingPost.id], navigationExtras);
  }
}
