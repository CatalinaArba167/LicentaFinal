import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import {MatDialog, MatDialogClose, MatDialogModule, MatDialogRef, MatDialogTitle} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {MatCommonModule} from "@angular/material/core";



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatCardModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 4000,
      positionClass: 'toast-bottom-center',
      preventDuplicates: true,
    }),
    MatDialogModule,
    MatButtonModule,
    MatCommonModule,


  ],
  exports:[
    MatCardModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule,
    BrowserAnimationsModule,
    ToastrModule,
    MatDialogModule,
    MatButtonModule,
    MatCommonModule
  ]
})
export class MaterialModule { }
