import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityReviewsMySuffix } from 'app/shared/model/entity-reviews-my-suffix.model';
import { EntityReviewsMySuffixService } from './entity-reviews-my-suffix.service';

@Component({
    selector: 'jhi-entity-reviews-my-suffix-delete-dialog',
    templateUrl: './entity-reviews-my-suffix-delete-dialog.component.html'
})
export class EntityReviewsMySuffixDeleteDialogComponent {
    entityReviews: IEntityReviewsMySuffix;

    constructor(
        private entityReviewsService: EntityReviewsMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityReviewsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'entityReviewsListModification',
                content: 'Deleted an entityReviews'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-reviews-my-suffix-delete-popup',
    template: ''
})
export class EntityReviewsMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ entityReviews }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EntityReviewsMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.entityReviews = entityReviews;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
