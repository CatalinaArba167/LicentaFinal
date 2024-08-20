import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarOfferCardComponent } from './components/car-offer-card/car-offer-card.component';
import {MaterialModule} from "../material.module";
import { MyCarCardComponent } from './components/my-car-card/my-car-card.component';
import { CustomDialogComponent } from './components/custom-dialog/custom-dialog.component';
import { CarDetailsCardComponent } from './components/car-details-card/car-details-card.component';
import { TechicalDetailsCardComponent } from './components/techical-details-card/techical-details-card.component';



@NgModule({
  declarations: [
    CarOfferCardComponent,
    MyCarCardComponent,
    CustomDialogComponent,
    CarDetailsCardComponent,
    TechicalDetailsCardComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
  ],
  exports: [
    CarOfferCardComponent,
    MyCarCardComponent,
    CustomDialogComponent,
    CarDetailsCardComponent,
    TechicalDetailsCardComponent
  ]
})
export class SharedModule { }
