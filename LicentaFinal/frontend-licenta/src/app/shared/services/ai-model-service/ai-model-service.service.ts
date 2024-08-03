import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {SellingPost} from "../../types/selling-post.types";
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AiModelServiceService {

  constructor(private http: HttpClient) { }

  makePrediction(formData: FormData): Observable<number> {
    return this.http.post<number>(`${environment.aiModelUrl}/predict`, formData);
  }

}
