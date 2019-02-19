import { Component, OnInit, Injectable } from '@angular/core';
import { UserService } from '../shared/services/user-info.service';
@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})

@Injectable()
export class ProfilePageComponent implements OnInit{
 
  public userinfos = [];

  constructor(private UserService: UserService) {

  }

  ngOnInit() {
      this.UserService.getUserInfo()
      .subscribe(data => this.userinfos = data);
  }

}