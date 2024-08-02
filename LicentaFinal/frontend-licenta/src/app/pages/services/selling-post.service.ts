import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../shared/types/authentication.types";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {SellingPost} from "../../shared/types/selling-post.types";

@Injectable({
  providedIn: 'root'
})
export class SellingPostService {

  constructor(private http: HttpClient) {}

  createSellingPost(formData: FormData): Observable<SellingPost> {
    return this.http.post<SellingPost>(environment.apiUrl + '/selling_post', formData)
  }

  updateSellingPost(formData: FormData): Observable<SellingPost> {
    return this.http.put<SellingPost>(environment.apiUrl + '/selling_post', formData)
  }

  getAllSellingPosts(userId: string): Observable<SellingPost[]> {
    return this.http.get<SellingPost[]>(`${environment.apiUrl}/selling_post/get_all/${userId}`);
  }

  getMySellingPost(userId: string): Observable<SellingPost[]> {
    return this.http.get<SellingPost[]>(`${environment.apiUrl}/selling_post/get_my/${userId}`);
  }

  getSellingPostById(sellingPostId: string): Observable<SellingPost> {
    return this.http.get<SellingPost>(`${environment.apiUrl}/selling_post/get_by_id/${sellingPostId}`);
  }
  deleteSellingPost(postId: string): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/selling_post/${postId}`);
  }
}
