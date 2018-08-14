/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Starkev2TestModule } from '../../../test.module';
import { EntityDetailsMySuffixDeleteDialogComponent } from 'app/entities/entity-details-my-suffix/entity-details-my-suffix-delete-dialog.component';
import { EntityDetailsMySuffixService } from 'app/entities/entity-details-my-suffix/entity-details-my-suffix.service';

describe('Component Tests', () => {
    describe('EntityDetailsMySuffix Management Delete Component', () => {
        let comp: EntityDetailsMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<EntityDetailsMySuffixDeleteDialogComponent>;
        let service: EntityDetailsMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityDetailsMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(EntityDetailsMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EntityDetailsMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityDetailsMySuffixService);
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
