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

  makePrediction(carPredictionData: {
    color: any;
    wheel: any;
    leather_interior: number;
    airbags: any;
    drive_wheels: any;
    cylinders: any;
    manufacturer: any;
    doors: any;
    gear_box_type: any;
    engine_volume: number;
    is_turbo: number;
    model: any;
    "prod._year": any;
    category: any;
    fuel_type: any;
    mileage: number
  }): Observable<number> {
    console.log(carPredictionData);
    return this.http.post<number>(`${environment.aiModelUrl}/predict`, carPredictionData);
  }

}
