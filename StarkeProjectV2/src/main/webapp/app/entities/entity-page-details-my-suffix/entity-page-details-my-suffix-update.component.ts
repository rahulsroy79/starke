import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';
import { EntityPageDetailsMySuffixService } from './entity-page-details-my-suffix.service';
import { IEntityPageFormatsMySuffix } from 'app/shared/model/entity-page-formats-my-suffix.model';
import { EntityPageFormatsMySuffixService } from 'app/entities/entity-page-formats-my-suffix';
import { IStarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';
import { StarkeEntityMySuffixService } from 'app/entities/starke-entity-my-suffix';

@Component({
    selector: 'jhi-entity-page-details-my-suffix-update',
    templateUrl: './entity-page-details-my-suffix-update.component.html'
})
export class EntityPageDetailsMySuffixUpdateComponent implements OnInit {
    private _entityPageDetails: IEntityPageDetailsMySuffix;
    isSaving: boolean;

    entitypageformats: IEntityPageFormatsMySuffix[];

    starkeentities: IStarkeEntityMySuffix[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private entityPageDetailsService: EntityPageDetailsMySuffixService,
        private entityPageFormatsService: EntityPageFormatsMySuffixService,
        private starkeEntityService: StarkeEntityMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ entityPageDetails }) => {
            this.entityPageDetails = entityPageDetails;
        });
        this.entityPageFormatsService.query({ filter: 'entitypagedetails-is-null' }).subscribe(
            (res: HttpResponse<IEntityPageFormatsMySuffix[]>) => {
                if (!this.entityPageDetails.entityPageFormatsId) {
                    this.entitypageformats = res.body;
                } else {
                    this.entityPageFormatsService.find(this.entityPageDetails.entityPageFormatsId).subscribe(
                        (subRes: HttpResponse<IEntityPageFormatsMySuffix>) => {
                            this.entitypageformats = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.starkeEntityService.query().subscribe(
            (res: HttpResponse<IStarkeEntityMySuffix[]>) => {
                this.starkeentities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.entityPageDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.entityPageDetailsService.update(this.entityPageDetails));
        } else {
            this.subscribeToSaveResponse(this.entityPageDetailsService.create(this.entityPageDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEntityPageDetailsMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEntityPageDetailsMySuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEntityPageFormatsById(index: number, item: IEntityPageFormatsMySuffix) {
        return item.id;
    }

    trackStarkeEntityById(index: number, item: IStarkeEntityMySuffix) {
        return item.id;
    }
    get entityPageDetails() {
        return this._entityPageDetails;
    }

    set entityPageDetails(entityPageDetails: IEntityPageDetailsMySuffix) {
        this._entityPageDetails = entityPageDetails;
    }
}
