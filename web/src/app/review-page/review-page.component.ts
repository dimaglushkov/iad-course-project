import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRoute } from "@angular/router";


@Component({
  selector: 'app-review-page',
  templateUrl: './review-page.component.html',
  styleUrls: ['./review-page.component.css']
})
export class ReviewPageComponent implements OnInit {

  user: Object;
  
  constructor(private route: ActivatedRoute) { 
     this.route.params.subscribe( params => this.user = params.nickname );
  }

  ngOnInit() {
  }

}
