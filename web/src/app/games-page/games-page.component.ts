import { Component, OnInit } from '@angular/core';
import { GameService } from '../shared/services/games.service';
import { Game, GameResponse, ResponseState } from '../interfaces';

@Component({
  selector: 'app-games-page',
  templateUrl: './games-page.component.html',
  styleUrls: ['./games-page.component.css']
})
export class GamesPageComponent implements OnInit {

  gameResponse: GameResponse;
  state: ResponseState;

  games: Game[] = []
  constructor( private gameService: GameService) { }

  ngOnInit() {
    this.gameService.getAllGames().subscribe(
      gameService => this.gameResponse = gameService
    )
  }

  addToLib(id){
    this.gameService.addToLib(id).subscribe(
      gameService => this.state = gameService
    )
    alert(this.state.description);
    
  }

}
