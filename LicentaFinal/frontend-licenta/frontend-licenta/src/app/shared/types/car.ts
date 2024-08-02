import {Location} from "./location.types";

export interface Car {
  id:string;
  location:Location;
  price:number;
  predictedPrice: number;
  manufacturer: string;
  model: string;
  prodYear: number;
  category: string;
  leatherInterior: boolean;
  fuelType: string;
  engineVolume: number;
  mileage: number;
  cylinders: number;
  gearBoxType: string;
  driveWheels: string;
  doors: string;
  wheel: string;
  color: string;
  airbags: number;
  isTurbo: boolean;
}
