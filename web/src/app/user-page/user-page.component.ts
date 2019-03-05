import { Component, OnInit, Injectable } from '@angular/core';
import { UserService } from '../shared/services/user.service'
import { Observable } from 'rxjs';
import { StringifyOptions } from 'querystring';
import { ActivatedRoute } from "@angular/router";


@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
@Injectable()
export class UserPageComponent implements OnInit {

  currentNickname: string;
  user: Object;
  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.currentNickname = localStorage.getItem('curNickname');
    this.route.params.subscribe(params => this.user = params.nickname);
    /*this.reviewsService.getUsersReviews(this.user).subscribe(
      reviewsService => this.reviews = reviewsService
    );*/
  }

}
