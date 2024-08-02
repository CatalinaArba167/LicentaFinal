import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {SellingPostService} from "../services/selling-post.service";
import {AuthenticationService} from "../../core/services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {SellingPost} from "../../shared/types/selling-post.types";

@Component({
  selector: 'app-edit-selling-post',
  templateUrl: './edit-selling-post.component.html',
  styleUrls: ['./edit-selling-post.component.scss']
})
export class EditSellingPostComponent implements OnInit {
  postForm: FormGroup;
  processedPictures: string[] = [];
  selectedFiles: File[] = [];
  sellingPost: SellingPost | null = null;

  fuelTypes: string[] = ['Diesel', 'Hybrid', 'Petrol', 'Electric'];
  gearBoxTypes: string[] = ['Automatic', 'Tiptronic', 'Manual', 'Variator'];
  drivingWheels: string[] = ['Front', '4x4', 'Rear'];
  doors: string[] = ['4-May', '2-Mar', '>5'];
  wheel: string[] = ['Left whee', 'Right-hand'];
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
              private router: Router,
              private route: ActivatedRoute) {

    // Access the sellingPost from router state
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.sellingPost = navigation.extras.state['sellingPost'] as SellingPost;
      this.processedPictures = navigation.extras.state['processedPictures'] as string[];
    }

    this.postForm = this.fb.group({
      title: ['', [Validators.required, Validators.maxLength(70)]],
      description: ['', [Validators.required, Validators.maxLength(1000)]],
      price: [null, [Validators.required, Validators.min(0)]],
      prodYear: ['', Validators.required],
      country: ['', Validators.required],
      county: ['', Validators.required],
      city: ['', Validators.required],
      manufacturer: ['', Validators.required],
      model: ['', Validators.required],
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
      leatherInterior: [false, Validators.required],
      isTurbo: [false, Validators.required],
    });
  }

  ngOnInit(): void {
    if (this.sellingPost) {
      this.populateForm();
      // Assuming your service provides URLs for existing images
    } else {
      // Handle case where sellingPost is not available in state (e.g., direct access)
      this.toastr.error('No data available. Please try again.', 'Error');
      this.router.navigate(['/home']);
    }
  }

  populateForm(): void {
    if (this.sellingPost) {
      this.postForm.patchValue({
        title: this.sellingPost.title,
        description: this.sellingPost.description,
        price: this.sellingPost.car.price,
        prodYear: this.sellingPost.car.prodYear,
        country: this.sellingPost.car.location.country,
        county: this.sellingPost.car.location.county,
        city: this.sellingPost.car.location.city,
        manufacturer: this.sellingPost.car.manufacturer,
        model: this.sellingPost.car.model,
        category: this.sellingPost.car.category,
        fuelType: this.sellingPost.car.fuelType,
        engineVolume: this.sellingPost.car.engineVolume,
        mileage: this.sellingPost.car.mileage,
        cylinders: this.sellingPost.car.cylinders,
        gearBoxType: this.sellingPost.car.gearBoxType,
        driveWheels: this.sellingPost.car.driveWheels,
        doors: this.sellingPost.car.doors,
        wheel: this.sellingPost.car.wheel,
        color: this.sellingPost.car.color,
        airbags: this.sellingPost.car.airbags,
        leatherInterior: this.sellingPost.car.leatherInterior === true ? true : false,
        isTurbo: this.sellingPost.car.isTurbo === true ? true : false,
      });
    }
  }

  onSubmit() {
    if (this.postForm.valid) {
      const postData = {
        ...this.sellingPost,
        title: this.postForm.value.title,
        description: this.postForm.value.description,
        car: {
          ...this.sellingPost?.car,
          location: {
            country: this.postForm.value.country,
            county: this.postForm.value.county,
            city: this.postForm.value.city
          },
          price: this.postForm.value.price,
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
        postDate: new Date().toISOString(),
      };

      const formData: FormData = new FormData();
      formData.append('sellingPost', new Blob([JSON.stringify(postData)], {type: 'application/json'}));

// Assuming processedPictures is a list of base64 strings
      this.processedPictures.forEach((base64String, index) => {
        // Convert base64 string to Blob
        const byteString = atob(base64String.split(',')[1]); // Decode base64 string
        const mimeString = base64String.split(',')[0].split(':')[1].split(';')[0]; // Extract mime type
        const arrayBuffer = new ArrayBuffer(byteString.length);
        const intArray = new Uint8Array(arrayBuffer);

        for (let i = 0; i < byteString.length; i++) {
          intArray[i] = byteString.charCodeAt(i);
        }

        const blob = new Blob([intArray], {type: mimeString});
        const file = new File([blob], `image_${index}.png`, {type: mimeString}); // Create a File object with a name

        formData.append('sellingPictures', file);
      });


      this.sellingPostService.updateSellingPost(formData).subscribe({
        next: () => {
          this.router.navigate(['/home']);
          this.toastr.success('Selling post updated successfully', 'Success');
        },
        error: (err) => {
          this.toastr.error('Failed to update post. Please check your input and try again.', 'Error');
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
          this.processedPictures.push(e.target.result); // Store base64 string
        };
        reader.readAsDataURL(file);
      }
    }
  }


  removeImage(index: number): void {
    if (index >= 0 && index < this.processedPictures.length) {
      this.processedPictures.splice(index, 1); // Remove from processed pictures
      this.selectedFiles.splice(index, 1);     // Remove from selected files
    }
  }
  protected predictThePrice(): void {
    if (this.sellingPost && this.sellingPost.car) {
      this.sellingPost.car.predictedPrice = 1000;
      console.log( this.sellingPost.car.predictedPrice)
    }
  }


}
