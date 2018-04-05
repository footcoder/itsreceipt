import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable()
export class UserService {

  constructor(private  http:HttpClient) { }

  signIn(data){
    return this.http.post('http://footcoder.niee.kr:8080/user/sign-in',data);
  }

  signup(){
    return this.http.post('http://localhost:8080/user/sign-up',data);
  }
}
