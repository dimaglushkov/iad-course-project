import { Component, OnInit } from '@angular/core';
import { UserInfo } from '../shared/services/interfaces';
import { runInThisContext } from 'vm';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit{
 
  public userinfos = [];

  constructor(private userInfo: UserInfo) {

  }

  ngOnInit() {
      this.userInfo.getUserInfo()
      .subscribe(data => this.userinfos = data);
  }

}
