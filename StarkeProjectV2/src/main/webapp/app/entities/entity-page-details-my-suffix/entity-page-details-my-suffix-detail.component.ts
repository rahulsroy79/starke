import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';

@Component({
    selector: 'jhi-entity-page-details-my-suffix-detail',
    templateUrl: './entity-page-details-my-suffix-detail.component.html'
})
export class EntityPageDetailsMySuffixDetailComponent implements OnInit {
    entityPageDetails: IEntityPageDetailsMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ entityPageDetails }) => {
            this.entityPageDetails = entityPageDetails;
        });
    }

    previousState() {
        window.history.back();
    }
}
