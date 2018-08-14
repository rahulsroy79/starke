import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IStarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';
import { StarkeEntityMySuffixService } from './starke-entity-my-suffix.service';
import { IEntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';
import { EntityPageDetailsMySuffixService } from 'app/entities/entity-page-details-my-suffix';
import { IEntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';
import { EntityDetailsMySuffixService } from 'app/entities/entity-details-my-suffix';

@Component({
    selector: 'jhi-starke-entity-my-suffix-update',
    templateUrl: './starke-entity-my-suffix-update.component.html'
})
export class StarkeEntityMySuffixUpdateComponent implements OnInit {
    private _starkeEntity: IStarkeEntityMySuffix;
    isSaving: boolean;

    entitypagedetails: IEntityPageDetailsMySuffix[];

    entitydetails: IEntityDetailsMySuffix[];

    starkeentities: IStarkeEntityMySuffix[];
    entitycreationdateDp: any;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private starkeEntityService: StarkeEntityMySuffixService,
        private entityPageDetailsService: EntityPageDetailsMySuffixService,
        private entityDetailsService: EntityDetailsMySuffixService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ starkeEntity }) => {
            this.starkeEntity = starkeEntity;
        });
        this.entityPageDetailsService.query({ filter: 'starkeentity-is-null' }).subscribe(
            (res: HttpResponse<IEntityPageDetailsMySuffix[]>) => {
                if (!this.starkeEntity.entityPageDetailsId) {
                    this.entitypagedetails = res.body;
                } else {
                    this.entityPageDetailsService.find(this.starkeEntity.entityPageDetailsId).subscribe(
                        (subRes: HttpResponse<IEntityPageDetailsMySuffix>) => {
                            this.entitypagedetails = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.entityDetailsService.query({ filter: 'starkeentity-is-null' }).subscribe(
            (res: HttpResponse<IEntityDetailsMySuffix[]>) => {
                if (!this.starkeEntity.entityDetailsId) {
                    this.entitydetails = res.body;
                } else {
                    this.entityDetailsService.find(this.starkeEntity.entityDetailsId).subscribe(
                        (subRes: HttpResponse<IEntityDetailsMySuffix>) => {
                            this.entitydetails = [subRes.body].concat(res.body);
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

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.starkeEntity, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.starkeEntity.id !== undefined) {
            this.subscribeToSaveResponse(this.starkeEntityService.update(this.starkeEntity));
        } else {
            this.subscribeToSaveResponse(this.starkeEntityService.create(this.starkeEntity));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStarkeEntityMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IStarkeEntityMySuffix>) => this.onSaveSuccess(),
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

    trackEntityPageDetailsById(index: number, item: IEntityPageDetailsMySuffix) {
        return item.id;
    }

    trackEntityDetailsById(index: number, item: IEntityDetailsMySuffix) {
        return item.id;
    }

    trackStarkeEntityById(index: number, item: IStarkeEntityMySuffix) {
        return item.id;
    }
    get starkeEntity() {
        return this._starkeEntity;
    }

    set starkeEntity(starkeEntity: IStarkeEntityMySuffix) {
        this._starkeEntity = starkeEntity;
    }
}
