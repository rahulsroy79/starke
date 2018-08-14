/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Starkev2TestModule } from '../../../test.module';
import { EntityReviewsMySuffixDeleteDialogComponent } from 'app/entities/entity-reviews-my-suffix/entity-reviews-my-suffix-delete-dialog.component';
import { EntityReviewsMySuffixService } from 'app/entities/entity-reviews-my-suffix/entity-reviews-my-suffix.service';

describe('Component Tests', () => {
    describe('EntityReviewsMySuffix Management Delete Component', () => {
        let comp: EntityReviewsMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<EntityReviewsMySuffixDeleteDialogComponent>;
        let service: EntityReviewsMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityReviewsMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(EntityReviewsMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EntityReviewsMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityReviewsMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
