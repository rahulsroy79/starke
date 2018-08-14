import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';

@Component({
    selector: 'jhi-entity-details-my-suffix-detail',
    templateUrl: './entity-details-my-suffix-detail.component.html'
})
export class EntityDetailsMySuffixDetailComponent implements OnInit {
    entityDetails: IEntityDetailsMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ entityDetails }) => {
            this.entityDetails = entityDetails;
        });
    }

    previousState() {
        window.history.back();
    }
}
