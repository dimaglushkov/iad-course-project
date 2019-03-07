import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RightsCheck, ResponseState } from '../interfaces';
import { SettingsService } from '../shared/services/settings.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  response: ResponseState;

  rigthsCheck: RightsCheck;
  isAdmin: boolean;

  name: string;
  surname: string;
  country: string;
  city: string;
  birthDate: Date;


  gameName: string;
  gameDate: Date;
  gameDesc: string;

  giveAdmNick: string;
  takeAdmNick: string;

  banNick: string;
  unbanNick: string;

  constructor(private settingService: SettingsService, private router: Router) { }

  ngOnInit() {
    this.settingService.checkRights().subscribe(
      settingsService => this.rigthsCheck = settingsService
    );
    this.isAdmin = this.rigthsCheck.admin;
  }

  sendInfo() {
    this.settingService.setInfo(this.name, this.surname, this.country, this.city, this.birthDate).subscribe(
      settingService => this.response = settingService
    );
    this.router.navigate(['user/' + localStorage.getItem('curNickname').toString]);
  }

  addGame() {
    this.settingService.addGame(this.gameName, this.gameDate, this.gameDesc).subscribe(
      settingService => this.response = settingService
    );
  }

  setAdmin() {
    this.settingService.grantRigths(this.giveAdmNick).subscribe(
      settingService => this.response = settingService
    );
  }

  stopAdmin() {
    this.settingService.ungrantRigths(this.takeAdmNick).subscribe(
      settingService => this.response = settingService
    );
  }

  ban() {
    this.settingService.ban(this.banNick).subscribe(
      settingService => this.response = settingService
    );
  }

  unban() {
    this.settingService.unban(this.banNick).subscribe(
      settingService => this.response = settingService
    );
  }






}
