import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from "../core/components/login/login.component";
import {MaterialModule} from "../material.module";
import { HeaderComponent } from '../core/components/header/header.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterLink} from "@angular/router";



@NgModule({
  declarations: [LoginComponent, HeaderComponent],
    imports: [
        CommonModule,
        MaterialModule,
        HttpClientModule,
        RouterLink
    ],
  exports:[
    LoginComponent,
    HeaderComponent,
    MaterialModule
  ]
})
export class CoreModule { }
