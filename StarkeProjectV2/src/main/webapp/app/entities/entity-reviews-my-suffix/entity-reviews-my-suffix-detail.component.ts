import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntityReviewsMySuffix } from 'app/shared/model/entity-reviews-my-suffix.model';

@Component({
    selector: 'jhi-entity-reviews-my-suffix-detail',
    templateUrl: './entity-reviews-my-suffix-detail.component.html'
})
export class EntityReviewsMySuffixDetailComponent implements OnInit {
    entityReviews: IEntityReviewsMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ entityReviews }) => {
            this.entityReviews = entityReviews;
        });
    }

    previousState() {
        window.history.back();
    }
}
