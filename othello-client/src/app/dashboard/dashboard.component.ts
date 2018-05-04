import { Component, Input, OnInit } from "@angular/core";
import { FormControl, FormGroup } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Account } from '../services/account/account';
import { AccountService } from "../services/account/account.service";
import { GameService } from "../services/game/game.service";
import { ShowGamesComponent } from "../showGames/showgames.component";

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

    @Input() player: Account;
    showCreateGameForm = false;
    showJoinGameForm = false;
    id: Number;
    showgames: ShowGamesComponent;

    constructor(private accountService: AccountService, private http: Http, private gameService: GameService) { }

    createGameGroup: FormGroup;
    private baseUrl: String = 'http://localhost:8080';

    ngOnInit() {
        if (!this.player) {
            this.player = this.accountService.loggedAccount;
        }
        this.id=this.player['id'];

        this.createGameGroup = new FormGroup({
            isBlack: new FormControl(),
            gameTime: new FormControl(),
        })
    }

    
    showCreateGame() {
        this.showCreateGameForm = true;
        this.showJoinGameForm = false;
    }

    showJoinGame() {
        this.showCreateGameForm = false;
        this.showJoinGameForm = true;
        this.gameService.showAvailableGames();
     //   .then((response) => {
     //       console.log(response);
     //   })
    //    let showgames = new ShowGamesComponent(this.accountService, this.http, this.gameService)
        //this.showgames.show();
    }
}

