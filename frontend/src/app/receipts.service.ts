import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from "@angular/router";

@Injectable()
export class ReceiptsService {

  receipts:any;

  constructor(private  http: HttpClient, private router:Router) {
  }

  getReceiptes(){
    this.http.get("")
  }
}
