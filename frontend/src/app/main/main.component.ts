import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from '../user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit() {
  }

  receiptForm() {
    if (this.userService.authenticated)
      this.router.navigateByUrl("write");
    else
      this.router.navigateByUrl("sign-in");
  }

  viewReceipt(id) {
    this.router.navigateByUrl("view/" + id);
  }
}
