import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  constructor( private route: ActivatedRoute,
               private router: Router
               ) { }

  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');
    console.log(id);
  }

}
