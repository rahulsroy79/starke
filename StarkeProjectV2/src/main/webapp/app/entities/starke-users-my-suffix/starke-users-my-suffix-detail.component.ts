import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';

@Component({
    selector: 'jhi-starke-users-my-suffix-detail',
    templateUrl: './starke-users-my-suffix-detail.component.html'
})
export class StarkeUsersMySuffixDetailComponent implements OnInit {
    starkeUsers: IStarkeUsersMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ starkeUsers }) => {
            this.starkeUsers = starkeUsers;
        });
    }

    previousState() {
        window.history.back();
    }
}
