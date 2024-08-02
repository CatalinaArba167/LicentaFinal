import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./core/components/login/login.component";
import {ChatComponent} from "./pages/chat/chat.component";
import {CreateSellingPostComponent} from "./pages/create-selling-post/create-selling-post.component";
import {AllPostsComponent} from "./pages/all-posts/all-posts.component";
import {MyCarCardComponent} from "./shared/components/my-car-card/my-car-card.component";
import {MyPostsComponent} from "./pages/my-posts/my-posts.component";
import {UserProfileComponent} from "./pages/user-profile/user-profile.component";
import {EditSellingPostComponent} from "./pages/edit-selling-post/edit-selling-post.component";
import {SellingPostDetailsComponent} from "./pages/selling-post-details/selling-post-details.component";

const routes: Routes = [
  {
    path: '',
    component:LoginComponent
  },
  {
    path: 'post',
    component:CreateSellingPostComponent
  },
  {
    path: 'chat',
    component: ChatComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'card',
    component: MyCarCardComponent,
  },
  {
    path: 'listings',
    component: MyPostsComponent,
  },
  {
    path: 'home',
    component: AllPostsComponent,
  },
  {
    path: 'profile',
    component: UserProfileComponent,
  },
  {
    path: 'edit/:id',
    component: EditSellingPostComponent,
  },
  {
    path: 'details/:id',
    component: SellingPostDetailsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers:[]
})
export class AppRoutingModule { }
