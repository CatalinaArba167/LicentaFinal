import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../shared/types/authentication.types";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  createUser(user: User): Observable<User> {
    return this.http.post<User>(environment.apiUrl + '/users', user)
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(environment.apiUrl + '/users', user)
  }


  getUser(userId: string): Observable<User> {
    return this.http.get<User>(`${environment.apiUrl}/users/${userId}`)
  }
}
