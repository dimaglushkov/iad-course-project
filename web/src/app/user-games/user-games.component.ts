import { Component, OnInit } from '@angular/core';
import { GameResponse, ResponseState } from '../interfaces';
import { GameService } from '../shared/services/games.service';

@Component({
  selector: 'app-user-games',
  templateUrl: './user-games.component.html',
  styleUrls: ['./user-games.component.css']
})
export class UserGamesComponent implements OnInit {
  
  gameResponse: GameResponse;
  state: ResponseState;
  isItYourPage: boolean;

  constructor(private gameService: GameService) { }

  ngOnInit() {
    this.gameService.getLib(localStorage.getItem('curPage')).subscribe(
      gameService => this.gameResponse = gameService
    )
    this.isItYourPage = localStorage.getItem('curPage') == localStorage.getItem('curNickname');
  }

  removeFromLib(id){
    this.gameService.removeFromLib(id).subscribe(
      gameService => this.state = gameService
    )
    alert(this.state.description);
  }




}
