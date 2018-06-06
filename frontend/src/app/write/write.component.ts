import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

import { UserService } from '../user.service';

@Component({
  selector: 'app-write',
  templateUrl: './write.component.html',
  styleUrls: ['./write.component.css']
})
export class WriteComponent implements OnInit {

  constructor(private userService:UserService, private router:Router) { }

  ngOnInit() {
    // if(!this.userService.authenticated){
    //   this.router.navigateByUrl("main");
    // }
  }

}
