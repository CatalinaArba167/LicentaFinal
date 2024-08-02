import {Component, OnInit} from '@angular/core';
import {SellingPostService} from "../services/selling-post.service";
import {AuthenticationService} from "../../core/services/authentication.service";
import {SellingPost} from "../../shared/types/selling-post.types";

@Component({
  selector: 'app-all-posts',
  templateUrl: './all-posts.component.html',
  styleUrls: ['./all-posts.component.scss']
})
export class AllPostsComponent implements OnInit{
  sellingPosts: SellingPost[] = [];

  constructor(private sellingPostService: SellingPostService, private authService:AuthenticationService) {
  }

  ngOnInit() {
    const userId = this.authService.getUserId();
    this.sellingPostService.getAllSellingPosts(userId!).subscribe({
      next: (data) => this.sellingPosts = data,
      error: (error) => console.error('Error fetching cars:', error)
    });
  }
}
