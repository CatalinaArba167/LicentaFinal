import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProfilePictureService {
  constructor(private http: HttpClient) { }

  uploadProfilePicture(userId: string, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('profilePicture', file);
    return this.http.post<void>(`${environment.apiUrl}/profile_picture/${userId}`, formData);
  }

  getProfilePicture(userId: string): Observable<Blob> {
    return this.http.get(`${environment.apiUrl}/profile_picture/${userId}`, { responseType: 'blob' });
  }
}
