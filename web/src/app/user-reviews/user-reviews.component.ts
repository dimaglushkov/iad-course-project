import { Component, OnInit } from '@angular/core';
import { ReviewsService } from '../shared/services/reviews.service';
import { ReviewResponse } from '../interfaces';

@Component({
  selector: 'app-user-reviews',
  templateUrl: './user-reviews.component.html',
  styleUrls: ['./user-reviews.component.css']
})
export class UserReviewsComponent implements OnInit {

  reviewResponse: ReviewResponse;

  constructor(private reviewsService: ReviewsService) { }

  ngOnInit() {
    this.reviewsService.getUsersReviews(localStorage.getItem('curPage')).subscribe(
      reviewsService => this.reviewResponse = reviewsService
    );

  }

}
