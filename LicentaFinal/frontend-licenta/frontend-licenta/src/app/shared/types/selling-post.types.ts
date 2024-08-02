import {Car} from "./car";

export interface SellingPost {
  id:string;
  ownerId:string;
  ownerFirstName:string;
  ownerLastName:string;
  ownerEmail:string;
  car:Car;
  available:string;
  title:string;
  description:string;
  postDate: string;
  sellingPictures:File[];
}
