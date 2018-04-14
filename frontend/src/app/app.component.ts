import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import * as $ from 'jquery';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor (private router:Router){

  }

  ngOnInit(){
  }

  routerChange(url){
    this.router.navigateByUrl(url);
    $('.navbar-toggler').click();
  }

}
