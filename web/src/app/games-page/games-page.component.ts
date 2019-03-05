import { Component, OnInit } from '@angular/core';
import { GameService } from '../shared/services/games.service';
import { Game } from '../interfaces';

@Component({
  selector: 'app-games-page',
  templateUrl: './games-page.component.html',
  styleUrls: ['./games-page.component.css']
})
export class GamesPageComponent implements OnInit {

  games: Game[] = []
  constructor( private gameService: GameService) { }

  ngOnInit() {
    this.gameService.fetch()
  }

}
