import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {SellingPostService} from "../services/selling-post.service";
import {AuthenticationService} from "../../core/services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-selling-post',
  templateUrl: './create-selling-post.component.html',
  styleUrls: ['./create-selling-post.component.scss'],
})
export class CreateSellingPostComponent {
  postForm: FormGroup;
  previewImages: string[] = [];
  selectedFiles: File[] = [];

  fuelTypes: string[] = ['Diesel', 'Hybrid', 'Petrol', 'Electric'];
  gearBoxTypes: string[] = ['Automatic', 'Tiptronic', 'Manual', 'Variator'];
  drivingWheels: string[] = ['Front', '4x4', 'Rear'];
  doors: string[] = ['4-May', '2-Mar', '>5'];
  wheel:string[]=['Left whee','Right-hand'];
  colors: string[] = [
    'Black', 'White', 'Silver', 'Grey', 'Blue', 'Red', 'Green', 'Orange',
    'Brown', 'Carnelian red', 'Beige', 'Golden', 'Sky blue', 'Yellow',
    'Purple', 'Pink'
  ];
  categories: string[] = [
    'Sedan', 'Jeep', 'Hatchback', 'Minivan', 'Coupe', 'Universal',
    'Microbus', 'Goods wagon', 'Pickup', 'Cabriolet', 'Limousine'
  ];
  manufacturers: string[] = [
    'TOYOTA', 'HYUNDAI', 'MERCEDES-BENZ', 'FORD', 'CHEVROLET', 'BMW',
    'HONDA', 'LEXUS', 'NISSAN', 'VOLKSWAGEN', 'SSANGYONG', 'OPEL',
    'KIA', 'MITSUBISHI', 'SUBARU', 'AUDI', 'MAZDA', 'JEEP', 'DAEWOO',
    'SUZUKI', 'DODGE', 'FIAT', 'PORSCHE', 'LAND ROVER', 'VAZ', 'MINI',
    'RENAULT', 'JAGUAR', 'CHRYSLER', 'INFINITI', 'ACURA', 'LINCOLN',
    'CADILLAC', 'SKODA', 'GMC', 'DAIHATSU', 'VOLVO', 'GAZ', 'BUICK',
    'PEUGEOT', 'CITROEN', 'SCION', 'UAZ', 'ISUZU', 'MOSKVICH',
    'MERCURY', 'BENTLEY', 'სხვა', 'FERRARI', 'HUMMER', 'MASERATI',
    'LANCIA', 'SAAB', 'TESLA', 'SEAT', 'ROVER', 'ZAZ', 'HAVAL',
    'ALFA ROMEO', 'LAMBORGHINI'
  ];

  constructor(private fb: FormBuilder, private toastr: ToastrService,
              private sellingPostService: SellingPostService,
              private authService: AuthenticationService,
              private router: Router) {

    this.postForm = this.fb.group({
      title: ['', [Validators.required, Validators.maxLength(70)]],
      description: ['', [Validators.required, Validators.maxLength(1000)]],
      price: [null, [Validators.required, Validators.min(0)]],
      country: ['', Validators.required],
      county: ['', Validators.required],
      city: ['', Validators.required],
      manufacturer: ['', Validators.required],
      model: ['', Validators.required],
      prodYear: ['', Validators.required],
      category: ['', Validators.required],
      fuelType: ['', Validators.required],
      engineVolume: ['', Validators.required],
      mileage: ['', Validators.required],
      cylinders: ['', Validators.required],
      gearBoxType: ['', Validators.required],
      driveWheels: ['', Validators.required],
      doors: ['', Validators.required],
      wheel: ['', Validators.required],
      color: ['', Validators.required],
      airbags: ['', Validators.required],
      leatherInterior:[false, Validators.required],
      isTurbo:[false, Validators.required],
    });
  }

  onSubmit() {
    if (this.postForm.valid) {
      const postData = {
        title: this.postForm.value.title,
        description: this.postForm.value.description,
        car: {
          location: {
            country: this.postForm.value.country,
            county: this.postForm.value.county,
            city: this.postForm.value.city
          },
          price: this.postForm.value.price,
          prodYear:this.postForm.value.prodYear,
          manufacturer: this.postForm.value.manufacturer,
          model: this.postForm.value.model,
          category: this.postForm.value.category,
          fuelType: this.postForm.value.fuelType,
          engineVolume: this.postForm.value.engineVolume,
          mileage: this.postForm.value.mileage,
          cylinders: this.postForm.value.cylinders,
          gearBoxType: this.postForm.value.gearBoxType,
          driveWheels: this.postForm.value.driveWheels,
          doors: this.postForm.value.doors,
          wheel: this.postForm.value.wheel,
          color: this.postForm.value.color,
          airbags: this.postForm.value.airbags,
          leatherInterior: this.postForm.value.leatherInterior,
          isTurbo: this.postForm.value.isTurbo,
        },
        ownerId: this.authService.getUserId()!.toString(),
        available: 'Y',
        postDate: new Date().toISOString(),
      };


      const formData: FormData = new FormData();
      formData.append('sellingPost', new Blob([JSON.stringify(postData)], {type: 'application/json'}));
      this.selectedFiles.forEach(file => {
        formData.append('sellingPictures', file, file.name);
      });

      this.sellingPostService.createSellingPost(formData).subscribe({
        next: () => {
          this.router.navigate(['/home']);
          this.toastr.success('Selling post created successfully', 'Success');
        },
        error: (err) => {
          this.toastr.error('Failed to create post. Please check your input and try again.', 'Error');
        }
      });
    } else {
      this.postForm.markAllAsTouched();
      this.toastr.error('Please ensure all fields are filled out correctly.', 'Form Incomplete');
    }
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      for (let i = 0; i < input.files.length; i++) {
        const file = input.files[i];
        this.selectedFiles.push(file);

        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.previewImages.push(e.target.result);
        };
        reader.readAsDataURL(file);
      }
    }
  }


  removeImage(index: number): void {
    this.previewImages.splice(index, 1);
    this.selectedFiles.splice(index, 1);
  }
}
