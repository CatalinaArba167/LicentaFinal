import {Component, Input} from '@angular/core';
import {SellingPost} from "../../types/selling-post.types";

@Component({
  selector: 'app-techical-details-card',
  templateUrl: './techical-details-card.component.html',
  styleUrls: ['./techical-details-card.component.scss']
})
export class TechicalDetailsCardComponent {
  @Input({required:true}) sellingPost!:SellingPost|null;


}
