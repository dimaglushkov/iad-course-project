import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from "@angular/router";
import { ReviewsService } from '../shared/services/reviews.service';
import { ReviewList } from '../interfaces';

@Component({
  selector: 'app-review-page',
  templateUrl: './review-page.component.html',
  styleUrls: ['./review-page.component.css']
})
export class ReviewPageComponent implements OnInit {

  user: Object;
  reviewsResponse: ReviewList;
  
  constructor(private route: ActivatedRoute, private router: Router, private reviewsService: ReviewsService) { 
     this.route.params.subscribe( params => this.user = params.nickname );
  }

  ngOnInit() {
    this.reviewsService.getUsersReviews(this.user).subscribe(
      reviewsService => this.reviewsResponse = reviewsService
    );
    if (this.reviewsResponse.state.success == false){
      localStorage.setItem('lastError', this.reviewsResponse.state.description);
      this.router.navigate(['error']);
    }
    
  }

}
