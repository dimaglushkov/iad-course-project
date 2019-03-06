import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-games',
  templateUrl: './user-games.component.html',
  styleUrls: ['./user-games.component.css']
})
export class UserGamesComponent implements OnInit {
  
  curPage: string;
  curNickname: string;

  constructor() { }

  ngOnInit() {
    this.curNickname = localStorage.getItem('curNickname');
    this.curPage = localStorage.getItem('curPage');
  }

}
