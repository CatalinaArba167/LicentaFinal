import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CoreModule} from "../core/core.module";
import { CreateSellingPostComponent } from './create-selling-post/create-selling-post.component';
import {MatSelectModule} from "@angular/material/select";
import { AllPostsComponent } from './all-posts/all-posts.component';
import { MyPostsComponent } from './my-posts/my-posts.component';
import {SharedModule} from "../shared/shared.module";
import { UserProfileComponent } from './user-profile/user-profile.component';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import { EditSellingPostComponent } from './edit-selling-post/edit-selling-post.component';
import { SellingPostDetailsComponent } from './selling-post-details/selling-post-details.component';



@NgModule({
  declarations: [
    CreateSellingPostComponent,
    AllPostsComponent,
    MyPostsComponent,
    UserProfileComponent,
    EditSellingPostComponent,
    SellingPostDetailsComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    CoreModule,
    MatSelectModule,
    ReactiveFormsModule,
    SharedModule,
    MatSlideToggleModule
  ]
})
export class PagesModule { }
