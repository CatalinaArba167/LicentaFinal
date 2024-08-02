import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from "./components/login/login.component";
import {MaterialModule} from "../material.module";
import { HeaderComponent } from './components/header/header.component';
import {MatIconModule} from "@angular/material/icon";
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
