import {Component} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {PATHS} from "../../../globals/routes";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  constructor(private authService: AuthenticationService, private router: Router) {
  }

  clickSignOut(): void {
    this.authService.logout();
  }

}
