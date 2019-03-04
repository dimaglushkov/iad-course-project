import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRoute } from "@angular/router";
import { ReviewsService } from '../shared/services/reviews.service';

@Component({
  selector: 'app-review-page',
  templateUrl: './review-page.component.html',
  styleUrls: ['./review-page.component.css']
})
export class ReviewPageComponent implements OnInit {

  user: Object;
  reviews: Object;
  
  constructor(private route: ActivatedRoute, private reviewsService: ReviewsService) { 
     this.route.params.subscribe( params => this.user = params.nickname );
  }

  ngOnInit() {
    //this.reviewsService.getUsersReviews(this.user).subscribe(
    //  reviewsService => this.user = reviewsService 
    //);
  }

}
