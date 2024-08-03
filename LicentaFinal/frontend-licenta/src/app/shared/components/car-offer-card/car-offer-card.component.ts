import {Component, Input, OnInit} from '@angular/core';
import {SellingPost} from "../../types/selling-post.types";
import {NavigationExtras, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-car-offer-card',
  templateUrl: './car-offer-card.component.html',
  styleUrls: ['./car-offer-card.component.scss']
})
export class CarOfferCardComponent implements OnInit{
  @Input() sellingPost!: SellingPost;
  firstImageSrc: string | undefined;
  processedPictures:string[]=[];
  defaultImageSrc: string = 'assets/default_selling_picture.png';

  constructor(private router: Router) {}

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

  protected navigateToDetails(): void {
    const navigationExtras: NavigationExtras = {
      state: { sellingPost: this.sellingPost, processedPictures:this.processedPictures
      }
    };
    this.router.navigate(['/details', this.sellingPost.id], navigationExtras);
  }

  protected formatNumber(value: number | undefined): string {
    if (!value && value !== 0) return '';

    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.');
  }
}
