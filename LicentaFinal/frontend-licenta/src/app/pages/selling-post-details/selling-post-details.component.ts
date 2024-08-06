import {Component, OnInit} from '@angular/core';
import {SellingPost} from "../../shared/types/selling-post.types";
import {Router} from "@angular/router";

@Component({
  selector: 'app-selling-post-details',
  templateUrl: './selling-post-details.component.html',
  styleUrls: ['./selling-post-details.component.scss']
})
export class SellingPostDetailsComponent implements OnInit{
  processedPictures: string[] = [];
  sellingPost: SellingPost | null = null;
  currentImageIndex: number = 0;
  defaultImageSrc: string = 'assets/default_selling_picture.png';

  constructor(private router: Router,) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.sellingPost = navigation.extras.state['sellingPost'] as SellingPost;
      this.processedPictures = navigation.extras.state['processedPictures'] as string[];
    }

  }

  prevImage(): void {
    if (this.currentImageIndex > 0) {
      this.currentImageIndex--;
    }
  }

  nextImage(): void {
    if (this.currentImageIndex < this.processedPictures.length - 1) {
      this.currentImageIndex++;
    }
  }

  navigateBack() {
    this.router.navigate(['/home']);
  }

  ngOnInit(): void {
    if(this.processedPictures.length===0){
      this.processedPictures.push(this.defaultImageSrc);
    }
  }
}
