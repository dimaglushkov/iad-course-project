import { Component, OnInit, Injectable } from '@angular/core';
import { UserService } from '../shared/services/user.service'
import { Observable } from 'rxjs';
import { User } from '../classes/user'

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
@Injectable()
export class UserPageComponent implements OnInit {

  user: Object;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUser(localStorage.getItem('curNickame')).subscribe(
      userService => this.user = userService
    )
  }

}
