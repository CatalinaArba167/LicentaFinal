import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {SellingPost} from "../../types/selling-post.types";
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {AiModel} from "../../types/ai_model.types";

@Injectable({
  providedIn: 'root'
})
export class AiModelServiceService {

  constructor(private http: HttpClient) { }

  makePrediction(carPredictionData: AiModel): Observable<number> {
    console.log(carPredictionData);
    return this.http.post<number>(`${environment.aiModelUrl}/predict`, carPredictionData);
  }

}
