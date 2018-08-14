import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';

@Component({
    selector: 'jhi-starke-user-types-my-suffix-detail',
    templateUrl: './starke-user-types-my-suffix-detail.component.html'
})
export class StarkeUserTypesMySuffixDetailComponent implements OnInit {
    starkeUserTypes: IStarkeUserTypesMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ starkeUserTypes }) => {
            this.starkeUserTypes = starkeUserTypes;
        });
    }

    previousState() {
        window.history.back();
    }
}
