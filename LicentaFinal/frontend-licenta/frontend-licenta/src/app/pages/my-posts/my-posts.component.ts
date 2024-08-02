import {Component, OnInit} from '@angular/core';
import {SellingPost} from "../../shared/types/selling-post.types";
import {SellingPostService} from "../services/selling-post.service";
import {AuthenticationService} from "../../core/services/authentication.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-my-posts',
  templateUrl: './my-posts.component.html',
  styleUrls: ['./my-posts.component.scss']
})
export class MyPostsComponent implements OnInit{
  sellingPosts: SellingPost[] = [];

  constructor(private sellingPostService: SellingPostService,
              private authService:AuthenticationService,
              private toastr:ToastrService) {
  }

  ngOnInit() {
    const userId = this.authService.getUserId();
    this.sellingPostService.getMySellingPost(userId!).subscribe({
      next: (data) => {this.sellingPosts = data; console.log(data)},
      error: (error) => console.error('Error fetching cars:', error)
    });


  }
  onDeleteConfirmed(post: SellingPost): void {
    this.sellingPostService.deleteSellingPost(post.id).subscribe({
      next: () => {
        this.toastr.success('Selling post deleted successfully', 'Success');
        this.sellingPosts = this.sellingPosts.filter(p => p.id !== post.id);
      },
      error: (error) => {
        this.toastr.error('Failed to delete the selling post', 'Error');
      }
    });
  }


}
