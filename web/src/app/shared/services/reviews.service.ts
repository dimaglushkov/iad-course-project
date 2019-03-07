import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReviewResponse } from 'src/app/interfaces';


@Injectable({
  providedIn: 'root'
})
export class ReviewsService {

  constructor(private http: HttpClient) { }

  public getUsersReviews(nickname){
    return this.http.get<ReviewResponse>('api/review/' + nickname);
  }

}
