import {Component, Input} from '@angular/core';
import {SellingPost} from "../../types/selling-post.types";
import {Router} from "@angular/router";

@Component({
  selector: 'app-car-details-card',
  templateUrl: './car-details-card.component.html',
  styleUrls: ['./car-details-card.component.scss']
})
export class CarDetailsCardComponent {
  @Input({required: true}) sellingPost!: SellingPost | null;


  formatNumber(value: number | undefined): string {
    if (!value && value !== 0) return '';

    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.');
  }


}
